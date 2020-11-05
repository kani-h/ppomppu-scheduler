package com.kanistyle.ppomppu.crawler.service.impl

import com.kanistyle.ppomppu.crawler.dto.LinkDto
import com.kanistyle.ppomppu.crawler.service.CrawlingService
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

/**
 * Created by Kani
 * Date: 2020/11/04
 *
 */
@Service
class PpomppuCrawlingServiceImpl : CrawlingService {
    companion object {
        const val BASE_URL = "http://www.ppomppu.co.kr/zboard/"
    }

    override fun findCrawling(baseUrl: String): List<LinkDto> {
        val listDtoList: MutableList<LinkDto> = mutableListOf()

        val conn: Connection = Jsoup.connect(baseUrl)
        val html: Document = conn.get()
        val elements: Elements = html.getElementById("revolution_main_table").select("tbody")
        var isFirst = true
        for (element in elements) {
            // 첫 라인이 광고..
            if (isFirst) {
                isFirst = false
                continue
            }
            val elm: Elements = element.getElementsByTag("tbody")
            for (el in elm) {
                val text: String = el.text()
                val href: String = el.getElementsByTag("a")[1].attr("href")
                listDtoList.add(LinkDto(text, BASE_URL + href))
            }
        }

        return listDtoList
    }
}