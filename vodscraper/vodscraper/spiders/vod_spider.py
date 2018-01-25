# coding=utf-8
import re

import scrapy
from vodscraper.items import VodScraperItem


class VodSpider(scrapy.Spider):
    name = "vod"
    allowed_domains = ["sports.news.naver.com"]
    start_urls = [
        "http://sports.news.naver.com/esports/vod/index.nhn"
    ]

    pattern = re.compile("\S+\s+vs\s+\S+\s+[12]\S+", re.UNICODE)

    def parse(self, response):
        for sel in response.xpath('//div[@class="video_list"]/ul/li/a[@class="videoImageLink"]'):
            item = VodScraperItem()

            item["url"] = sel.xpath('@href').extract()
            item["title"] = sel.xpath('div[@class="text"]/span[@class="title"]/text()').extract()

            if self.pattern.search(item["title"][0]):
                print item["title"][0], ("http://sports.news.naver.com/"+item["url"][0])
                yield item