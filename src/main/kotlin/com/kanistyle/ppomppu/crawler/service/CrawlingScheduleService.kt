package com.kanistyle.ppomppu.crawler.service

import com.kanistyle.ppomppu.crawler.dto.LinkDto
import com.kanistyle.ppomppu.crawler.service.impl.CoolNJoyCrawlingServiceImpl
import com.kanistyle.ppomppu.crawler.service.impl.PpomppuCrawlingServiceImpl
import org.springframework.stereotype.Service

/**
 * Created by Kani
 * Date: 2020/11/05
 *
 */
@Service
class CrawlingScheduleService(private val coolNJoyCrawlerService: CoolNJoyCrawlingServiceImpl,
                              private val ppomppuCrawlerService: PpomppuCrawlingServiceImpl,
                              private val newArrivalMap: HashMap<String, Long>) {

    /* Cool n Joy */
    fun coolNJoyCrawling() {
        val urlList = mutableListOf("https://coolenjoy.net/bbs/jirum?sca=PC%EA%B4%80%EB%A0%A8",
                "https://coolenjoy.net/bbs/jirum?sca=%EA%B0%80%EC%A0%84",
                "https://coolenjoy.net/bbs/jirum?sca=PC%EA%B4%80%EB%A0%A8")
        crawling(urlList, coolNJoyCrawlerService) { url -> Regex("[^0-9]").replace(url.substring(0, url.indexOf("?")), "").toLong() }
    }

    /* Ppomppu */
    fun ppomppuCrawling() {
        val urlList = mutableListOf("http://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu&category=4",
                "http://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu&category=5")
        crawling(urlList, ppomppuCrawlerService) { url -> Regex("[^0-9]").replace(url.substring(url.indexOf("no=")),"").toLong() }
    }

    private fun crawling(urlList: MutableList<String>, crawlingService: CrawlingService, findRowId: (String) -> Long) {
        urlList.forEach urlListForEach@{ url: String ->
            val linkDtoList: List<LinkDto> = crawlingService.findCrawling(url)
            linkDtoList.forEach { linkDto: LinkDto ->
                if (newArrivalMap.containsKey(url)) {
                    if (newArrivalMap[url]!! >= findRowId(linkDto.link)) {
                        newArrivalMap[url] = findRowId(linkDtoList.first().link)
                        return@urlListForEach
                    } else {
                        // TODO Send Messing
                        println(linkDto)
                    }
                } else {
                    newArrivalMap[url] = findRowId(linkDtoList.first().link)
                    return@urlListForEach
                }
            }
        }
    }
}