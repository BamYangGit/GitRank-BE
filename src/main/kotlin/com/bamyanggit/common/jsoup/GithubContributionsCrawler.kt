package com.bamyanggit.common.jsoup

import com.bamyanggit.common.jsoup.dto.ContributionsResponse
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.regex.Pattern

@Component
class GithubContributionsCrawler {

    companion object {
        const val URL = "https://github.com/users"
        const val TEXT = ".js-yearly-contributions"
        const val CONTRIBUTIONS = ".f4.text-normal.mb-2"
    }

    fun getContributionCount(targetUserName: String, currentDate: LocalDate = LocalDate.now()): ContributionsResponse {
        val url = "$URL/$targetUserName/contributions"
        val elements = Jsoup.connect(url).get().select(TEXT)
        val contributionsHTML = elements.text()

        val totalContributionsHTML = elements.select(CONTRIBUTIONS).text()
        val totalCommit = totalContributionsHTML.split(" ")[0].replace(",", "").toInt()

        val contributionsMap = mutableMapOf<LocalDate, Int>()
        val regex = "(\\d+) contributions on (\\w+ \\d)"
        val pattern = Pattern.compile(regex);
        val matcher = pattern.matcher(contributionsHTML);

        while (matcher.find()) {
            val contributions = matcher.group(1)
            val target = matcher.group(2).split(" ")
            val date = dateFormat("2024 " + target[0] + " " + target[1].padStart(2, '0'))
            // 2024 -> Date change 필요 임시 값

            contributionsMap[date] = contributions.toInt()
        }

        val todayCommit = contributionsMap[currentDate] ?: 0
        val yesterdayCommit = contributionsMap[currentDate.minusDays(1)] ?: 0

        return ContributionsResponse(
            totalCommit = totalCommit,
            todayCommit = todayCommit,
            yesterdayCommit = yesterdayCommit,
        )
    }

    private fun dateFormat(target: String): LocalDate {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd", Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val formattedDate = LocalDate.parse(target.trim(), inputFormatter)
            .format(outputFormatter)
            .split("-")
        val year = formattedDate[0].toInt()
        val month = formattedDate[1].toInt()
        val day = formattedDate[2].toInt()

        return LocalDate.of(year, month, day)
    }
}
