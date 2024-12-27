package com.newsfeed.fakebook.dto.feed;

import java.util.ArrayList;
import java.util.List;

public class PagingFeedResponseDto {
	private int totalCount;
	private int totalPage;
	private int currentPage;
	private List<FeedResponseDto> feedResponseDtoList = new ArrayList();

	public PagingFeedResponseDto(int totalCount, int totalPage, int currentPage, List<FeedResponseDto> feedResponseDtoList) {
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.feedResponseDtoList = feedResponseDtoList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<FeedResponseDto> getFeedResponseDtoList() {
		return feedResponseDtoList;
	}
}
