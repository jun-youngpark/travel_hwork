package com.triple.travel.tripleCity;

import com.triple.travel.common.ControllerHandler;
import com.triple.travel.common.Vaild.update;
import com.triple.travel.common.Vaild.create;
import com.triple.travel.tripleCity.dto.TripleCityRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/city")
@Api("트리플 도시 등록 API(admin)")
public class TripleCityController extends ControllerHandler {

    @Autowired
    private TripleCityService tripleCityService;

    @ApiOperation(value="트리플 도시 등록 API", notes="새로운 도시(트리플)를 생성합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated(create.class) TripleCityRequestDto tripleCityRequestDto) {
        return success(tripleCityService.save(tripleCityRequestDto));
    }

    @ApiOperation(value="트리플 도시 수정 API", notes="등록된 도시(트리플)를 수정합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody @Validated(update.class) TripleCityRequestDto tripleCityRequestDto) {
        return success(tripleCityService.modify(tripleCityRequestDto));
    }

    @ApiOperation(value="트리플 도시 단건조회 API", notes="단일 도시(트리플)를 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/get/{tripleCitySeq}")
    public ResponseEntity<?> get(@PathVariable Long tripleCitySeq) {
        return success(tripleCityService.get(tripleCitySeq));
    }

    @ApiOperation(value="트리플 도시 삭제 API", notes="단일 도시(트리플)를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @DeleteMapping("/delete/{tripleCitySeq}")
    public ResponseEntity<?> delete(@PathVariable Long tripleCitySeq) {
        return success(tripleCityService.delete(tripleCitySeq));
    }


    @ApiOperation(value="사용자 별 목록 조회 API", notes="사용자 별 최적의 도시를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 400, message = "잘못된 요청값"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/listbyuser/{userId}")
    public ResponseEntity<?> listByUser(@PathVariable String userId) {
        return success(tripleCityService.listByUser(userId));
    }

}
