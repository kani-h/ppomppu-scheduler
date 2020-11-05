package com.kanistyle.ppomppu.crawler.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Kani
 * Date: 2020/11/05
 *
 */
@Configuration
class BeanConfig {

    @Bean
    fun newArrivalMap(): HashMap<String, Long>{
        return HashMap()
    }
}