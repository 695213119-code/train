package com.train.coreservice.api;

import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.DictionariesAddDTO;
import com.train.coreservice.dto.DictionariesEditDTO;
import com.train.coreservice.service.IDataDictionaryService;
import com.train.coreservice.vo.DataDictionariesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典API
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@RestController
@RequestMapping("/api/dataDictionary")
@Api(tags = "数据字典API", hidden = true)
public class DataDictionaryApiController {

    private final IDataDictionaryService dataDictionaryService;

    @Autowired
    public DataDictionaryApiController(IDataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    @ApiOperation("添加字典")
    @PostMapping("/addDictionaries")
    public RespRecurrence addDictionaries(@ApiParam(value = "字典参数类", required = true) @RequestBody DictionariesAddDTO dictionariesAddDTO,
                                          BindingResult bindingResult) {
        return dataDictionaryService.addDictionaries(dictionariesAddDTO, bindingResult);
    }

    @ApiOperation("操作字典")
    @PutMapping("/editDictionaries")
    public RespRecurrence editDictionaries(@ApiParam(value = "字典参数类", required = true) @RequestBody DictionariesEditDTO dictionariesEditDTO,
                                           BindingResult bindingResult) {
        return dataDictionaryService.editDictionaries(dictionariesEditDTO, bindingResult);
    }

    @ApiOperation(value = "获取字典列表",response = DataDictionariesVO.class)
    @GetMapping("/queryDictionaries")
    public RespPageRecurrence queryDictionaries(
            @ApiParam(value = "页码", required = true, example = "1") @RequestParam Integer page,
            @ApiParam(value = "每页条数", required = true, example = "10") @RequestParam Integer limit,
            @ApiParam(value = "字典的key") @RequestParam(required = false) String key,
            @ApiParam(value = "字典的备注") @RequestParam(required = false) String remarks) {
        return dataDictionaryService.queryDictionaries(page, limit, key, remarks);
    }


}
