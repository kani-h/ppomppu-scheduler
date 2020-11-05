package com.kanistyle.ppomppu.crawler.scheduler

import com.kanistyle.ppomppu.crawler.service.CrawlingScheduleService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by Kani
 * Date: 2020/11/05
 *
 */
@Component
class CrawlingScheduler(private val crawlingScheduleService: CrawlingScheduleService) {

    @Scheduled(fixedRate = 30000, initialDelay = 3000)
    fun coolNJoy() {
        crawlingScheduleService.coolNJoyCrawling()
    }

    @Scheduled(fixedRate = 30000, initialDelay = 3000)
    fun ppomppu() {
        crawlingScheduleService.ppomppuCrawling()
    }
}