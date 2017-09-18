package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.GET;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.FieldAPI;
import com.bjike.goddess.task.api.RowAPI;
import com.bjike.goddess.task.api.TableAPI;
import com.bjike.goddess.task.bo.TableBO;
import com.bjike.goddess.task.dto.RowDTO;
import com.bjike.goddess.task.to.RowTO;
import com.bjike.goddess.task.vo.FieldVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("row")
public class RowAct extends BaseFileAction {
    @Autowired
    private RowAPI rowAPI;
    @Autowired
    private FieldAPI fieldAPI;
    @Autowired
    private TableAPI tableAPI;

    /**
     * 添加一行数据
     *
     * @param to 行数据
     * @return
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RowTO to, BindingResult rs, HttpServletRequest request) throws ActException {
        try {
            List<FieldVO> fields = fieldAPI.list(to.getTableId(), to.getNode());
            Map<String, String> fieldValMap = new HashMap<>();
            for (FieldVO vo : fields) {
                String val = request.getParameter(vo.getName());
                if (StringUtils.isNotBlank(val)) {
                    fieldValMap.put(vo.getName(), val);
                } else {
                    if (vo.isNeed()) {
                        throw new SerException(vo.getName() + "不能为空");
                    }
                }
            }
            rowAPI.add(fieldValMap, to.getTableId(), to.getNode());
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 从excel导入
     *
     * @param dto
     * @return
     * @throws ActException
     */
    @PostMapping("v1/excel/import")
    public Result excelImport(HttpServletRequest request, @Validated({ADD.class}) RowDTO dto, BindingResult rs) throws ActException {
        try {
            List<InputStream> inputStreams = getInputStreams(request);
            rowAPI.excelImport(inputStreams, dto);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 从excel导出
     *
     * @param dto
     * @return
     * @throws ActException
     */
    @GetMapping("v1/excel/export")
    public Result excelExport(@Validated({GET.class}) RowDTO dto, BindingResult rs, HttpServletResponse response) throws ActException {
        try {
            try {
                TableBO table = tableAPI.findById(dto.getTableId());
                byte[] bytes= rowAPI.excelExport(dto);
                writeOutFile(response, bytes, table.getName() + ".xlsx");
            } catch (Exception e) {

            }
            return ActResult.initialize(rowAPI.excelExport(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws ActException
     */
    @GetMapping("v1/list")
    public Result excelExport(@Validated({GET.class}) RowDTO dto, BindingResult rs) throws ActException {
        try {
            return ActResult.initialize(rowAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
