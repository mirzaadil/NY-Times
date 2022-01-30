package com.mirza.adil.nytimes

import com.mirza.adil.nytimes.model.News
import com.mirza.adil.nytimes.model.Result

class MockTestUtil {
    companion object {
        fun createNews() = News(
            numResults = 3,
            results = createResult(),
            status = "success"
        )

         fun createResult() = listOf(
            Result(
                description = "this is description",
                byline = "byline",
                media = null,
                title = "News Title 1",
                updated = "2022-12-01",
                type = "news"
                ),
            Result(
                description = "this is description",
                byline = "byline",
                media = null,
                title = "News Title 2",
                updated = "2022-12-01",
                type = "news"
            ),
            Result(
                description = "this is description",
                byline = "byline",
                media = null,
                title = "News Title 3",
                updated = "2022-12-01",
                type = "news"
            )
        )
    }
}