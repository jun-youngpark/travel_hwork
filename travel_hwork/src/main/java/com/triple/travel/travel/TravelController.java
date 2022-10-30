package com.triple.travel.travel;

import com.triple.travel.common.ControllerHandler;
import com.triple.travel.common.exception.ApiException;
import com.triple.travel.travel.dto.TravelRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.triple.travel.common.Vaild.update;
import com.triple.travel.common.Vaild.create;
import com.triple.travel.common.Vaild.get;
import com.triple.travel.common.Vaild.delete;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/travel")
@Api("여행지 API")
public class TravelController extends ControllerHandler {

    @Autowired
    private TravelService travelService;

    @ApiOperation(value="여행 등록 API", notes="새로운 여행일정을 생성합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated(create.class) TravelRequestDto tripleCityRequestDto) {
        return success(travelService.save(tripleCityRequestDto));
    }
    @ApiOperation(value="여행 수정 API", notes="여행일정을 수정합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody @Validated(update.class) TravelRequestDto tripleCityRequestDto) {
        return success(travelService.modify(tripleCityRequestDto));
    }

    @ApiOperation(value="단일 여행 조회 API", notes="단일 여행을 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/get/{travelId}")
    public ResponseEntity<?> get(@PathVariable Long travelId) {
        return success(travelService.get(travelId).orElseThrow(() -> new ApiException("존재하지않는 여행 목록입니다.")));
    }

    @ApiOperation(value="여행지 삭제 API", notes="여행지를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @DeleteMapping("/delete/{travelId}")
    public ResponseEntity<?> delete(@PathVariable Long travelId) {
        return success(travelService.delete(travelId));
    }
}
