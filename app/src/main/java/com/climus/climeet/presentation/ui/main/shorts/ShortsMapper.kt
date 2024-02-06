package com.climus.climeet.presentation.ui.main.shorts

import com.climus.climeet.data.model.response.SearchAvailableGymItem
import com.climus.climeet.data.model.response.ShortsItem
import com.climus.climeet.data.model.response.ShortsUpdatedFollowResponse
import com.climus.climeet.presentation.ui.intro.signup.admin.model.SearchCragUiData
import com.climus.climeet.presentation.ui.main.shorts.model.ShortsThumbnailUiData
import com.climus.climeet.presentation.ui.main.shorts.model.ShortsUiData
import com.climus.climeet.presentation.ui.main.shorts.model.UpdatedFollowUiData
import com.climus.climeet.presentation.util.Constants.climeetColor

fun SearchAvailableGymItem.toSearchCragUiData(
    keyword: String,
    onClickListener: (Long, String, String) -> Unit
) = SearchCragUiData(
    id = id,
    imgUrl = profileImageUrl,
    name = name,
    keyword = keyword,
    onClickListener = onClickListener
)

fun ShortsItem.toShortsThumbnailUiData(
    onClickListener: (Long) -> Unit
) = ShortsThumbnailUiData(
    shortsId = shortsId,
    thumbnailImg = thumbnailImageUrl,
    gymName = gymName,
    originLevelColor = shortsDetailInfo.gymDifficultyColor,
    climeetLevelColor = "#DDDDDD",
    onClickListener = onClickListener
)

fun ShortsItem.toShortsUiData() : ShortsUiData{

    var climeetLevelColor = "#FFFFFF"
    climeetColor[climeetDifficultyName]?.let{
       climeetLevelColor = it
    }

    return ShortsUiData(
        shortsId = shortsId,
        thumbnailImg = thumbnailImageUrl,
        gymId = shortsDetailInfo.gymId,
        gymName = gymName,
        climeetLevelColor = climeetLevelColor,
        videoUrl = shortsDetailInfo.videoUrl,
        profileImgUrl = shortsDetailInfo.userShortsSimpleInfo.profileImgUrl,
        userName = shortsDetailInfo.userShortsSimpleInfo.profileName,
        description = shortsDetailInfo.description,
        sectorId = shortsDetailInfo.sectorId,
        sectorName = shortsDetailInfo.sectorName,
        sectorImgUrl = shortsDetailInfo.routeImageUrl,
        likeCount = shortsDetailInfo.likeCount,
        commentCount = shortsDetailInfo.commentCount,
        bookMarkCount = shortsDetailInfo.bookmarkCount,
        sharedCount = shortsDetailInfo.shareCount,
        isSoundEnabled = shortsDetailInfo.isSoundEnabled,
        isLiked = shortsDetailInfo.liked,
        isBookMarked = shortsDetailInfo.bookmarked
    )
}



fun ShortsUpdatedFollowResponse.toUpdatedFollowUiData(
    viewType: Int,
    onClickListener: (Long) -> Unit,
    navigateToAddFollow: () -> Unit
) = UpdatedFollowUiData(
    userId = followingUserId,
    viewType = viewType,
    profileImg = followingUserProfileUrl,
    name = followingUserName,
    onClickListener = onClickListener,
    navigateToAddFollow = navigateToAddFollow
)