package com.kanistyle.ppomppu

import com.kanistyle.ppomppu.crawler.service.impl.CoolNJoyCrawlingServiceImpl
import com.kanistyle.ppomppu.crawler.service.impl.PpomppuCrawlingServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PpomppuAlrmApplicationTests {
    @Autowired
    lateinit var coolNJoyCrawlerService: CoolNJoyCrawlingServiceImpl

    @Autowired
    lateinit var ppomppuCrawlerService: PpomppuCrawlingServiceImpl

    @Test
    fun coolNJoyCrawlerServiceTest() {
        println(coolNJoyCrawlerService.findCrawling("https://coolenjoy.net/bbs/jirum?sca=PC%EA%B4%80%EB%A0%A8"))
    }

    @Test
    fun ppomppuCrawlerServiceTest() {
        println(ppomppuCrawlerService.findCrawling("http://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu&category=4"))
    }


}
