package com.kanistyle.ppomppu.crawler.service

import com.kanistyle.ppomppu.crawler.dto.LinkDto

/**
 * Created by Kani
 * Date: 2020/11/04
 *
 */
interface CrawlingService {
    fun findCrawling(baseUrl: String): List<LinkDto>
}