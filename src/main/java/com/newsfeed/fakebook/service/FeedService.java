package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.feed.FeedRequestDto;
import com.newsfeed.fakebook.dto.feed.FeedResponseDto;
import com.newsfeed.fakebook.dto.feed.PagingFeedResponseDto;
import com.newsfeed.fakebook.repository.CommentRepository;
import com.newsfeed.fakebook.repository.FeedRepository;
import com.newsfeed.fakebook.repository.LikeRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {
	private final UserRepository userRepository;
	private final FeedRepository feedRepository;

	private static final int DEFAULT_PAGE_SIZE = 10;
	private final CommentRepository commentRepository;
	private final LikeRepository likeRepository;

	public Long createFeed(Long userId, FeedRequestDto request) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

		Feed feed = Feed.builder()
				.user(user)
				.content(request.getContent())
				.build();
		return feedRepository.save(feed).getFeedId();
	}

	public PagingFeedResponseDto getAllFeeds(int currentPage, Integer size) {

		PageRequest pageRequest = PageRequest.of(
				currentPage, size == null ? DEFAULT_PAGE_SIZE : size,
				Sort.by(Sort.Direction.DESC, "postedTime")
		);
		Page<Feed> feedPage = feedRepository.findAll(pageRequest);
		int totalCount = (int) feedPage.getTotalElements();
		int totalPageCount = feedPage.getTotalPages();
		List<Feed> feedList = feedPage.getContent();

		List<FeedResponseDto> feedResponseDtoList = feedList.stream().map(feed -> {
					Long commentCount = commentRepository.countByFeed_FeedId(feed.getFeedId());
					Long likeCount = likeRepository.countByFeed_FeedId(feed.getFeedId());
					return new FeedResponseDto(
							feed,
							commentCount,
							likeCount
					);
				}
		).toList();

		return new PagingFeedResponseDto(
				totalCount,
				totalPageCount,
				currentPage,
				feedResponseDtoList
		);
	}

	public FeedResponseDto getFeed(Long feedID) {
		Feed feed = feedRepository.findById(feedID)
				.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
		Long commentCount = commentRepository.countByFeed_FeedId(feed.getFeedId());
		Long likeCount = likeRepository.countByFeed_FeedId(feed.getFeedId());
		return new FeedResponseDto(feed,commentCount,likeCount);
	}

	public void updateFeed(Long feedId, FeedRequestDto request) {
		Feed feed = feedRepository.findById(feedId)
				.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
		feed.update(request.getContent());
	}

	public void deleteFeed(Long feedId) {
		feedRepository.deleteById(feedId);
	}

//	public List<FeedResponseDto> getUserFeeds(Long userId, int currentPage, Integer size) {
//
//		PageRequest pageRequest = PageRequest.of(
//				currentPage, size == null ? DEFAULT_PAGE_SIZE : size,
//				Sort.by(Sort.Direction.DESC, "postedTime")
//		);
//		Page<Feed> feedPage = feedRepository.findAll(pageRequest);
//		int totalCount = (int) feedPage.getTotalElements();
//		int totalPageCount = feedPage.getTotalPages();
//		List<Feed> feedList = feedPage.getContent();
//
//		List<FeedResponseDto> feedResponseDtoList = feedList.stream().map(feed -> {
//					Long commentCount = commentRepository.countByFeedId(feed.getFeedId());
//					Long likeCount = likeRepository.countByFeedId(feed.getFeedId());
//					return new FeedResponseDto(
//							feed,
//							commentCount,
//							likeCount
//					);
//				}
//		).toList();
//
//		return new PagingFeedResponseDto(
//				totalCount,
//				totalPageCount,
//				currentPage,
//				feedResponseDtoList
//		);
//
//		return feedRepository.findAllByUserId(userId).stream().map(FeedResponseDto::new).toList();
//	}

}
