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
class CoolNJoyCrawlingServiceImpl : CrawlingService {
    override fun findCrawling(baseUrl: String): List<LinkDto> {
        val listDtoList: MutableList<LinkDto> = mutableListOf()

        val conn: Connection = Jsoup.connect(baseUrl)
        val html: Document = conn.get()

        val elements: Elements = html.select("tbody")
        for (element in elements) {
            val elm: Elements = element.getElementsByTag("a")
            for (el in elm) {
                val text: String = el.text()
                val href: String = el.attr("href")

                listDtoList.add(LinkDto(text, href))
            }
        }

        return listDtoList
    }
}