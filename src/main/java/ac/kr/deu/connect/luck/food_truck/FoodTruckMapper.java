package ac.kr.deu.connect.luck.food_truck;


import ac.kr.deu.connect.luck.food_truck.dto.*;
import ac.kr.deu.connect.luck.food_truck.entity.FoodTruck;
import ac.kr.deu.connect.luck.food_truck.entity.FoodTruckMenu;
import ac.kr.deu.connect.luck.food_truck.entity.FoodTruckReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodTruckMapper {

    /**
     * 푸드트럭 생성 요청 DTO를 푸드트럭 엔티티로 변환합니다.
     *
     * @param foodTruckRequest 푸드트럭 생성 요청 DTO
     * @return 푸드트럭 엔티티
     */
    FoodTruck toFoodTruck(FoodTruckRequest foodTruckRequest);

    /**
     * 푸드트럭 엔티티를 푸드트럭 상세 응답 DTO로 변환합니다.
     *
     * @param foodTruck 푸드트럭 엔티티
     * @return 푸드트럭 상세 응답 DTO
     */
    @Mapping(target = "managerName", source = "manager.name")
    FoodTruckDetailResponse toFoodTruckDetailResponse(FoodTruck foodTruck);

    /**
     * 푸드트럭 검색 시 소량의 정보만 보여주기 위한 DTO
     *
     * @param foodTruck 푸드트럭 엔티티
     * @return 푸드트럭 검색 시 소량의 정보만 보여주기 위한 DTO
     */
    @Mapping(target = "managerName", source = "manager.name")
    @Mapping(target = "avgScore", expression = "java(foodTruck.getReviews().stream().mapToDouble(FoodTruckReview::getScore).average().orElse(0))")
    @Mapping(target = "reviewCount", expression = "java(foodTruck.getReviews().size())")
    FoodTruckHeader toFoodTruckHeader(FoodTruck foodTruck);

    /**
     * 푸드트럭 메뉴 엔티티를 푸드트럭 메뉴 응답 DTO로 변환합니다.
     *
     * @param foodTruckMenu 푸드트럭 메뉴 엔티티
     * @return 푸드트럭 메뉴 응답 DTO
     */
    FoodTruckMenuResponse toFoodTruckMenuResponse(FoodTruckMenu foodTruckMenu);

    /**
     * 푸드트럭 리뷰 엔티티를 푸드트럭 리뷰 응답 DTO로 변환합니다.
     *
     * @param foodTruckReview 푸드트럭 리뷰 엔티티
     * @return 푸드트럭 리뷰 응답 DTO
     */
    @Mapping(target = "authorName", source = "author.name")
    FoodTruckReviewResponse toFoodTruckReviewResponse(FoodTruckReview foodTruckReview);
}
