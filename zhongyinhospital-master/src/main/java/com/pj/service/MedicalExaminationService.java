package com.pj.service;

import com.pj.dto.medicalExaminationInfoReqVO;
import com.pj.entity.MedicalExamination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 体检报告(MedicalExamination)表服务接口
 *
 * @author makejava
 * @since 2023-07-14 10:54:54
 */
public interface MedicalExaminationService {

    /**
     * 门珍完成
     */
    int upd(medicalExaminationInfoReqVO medicalExaminationInfoReqVO);
    /**
     * 根据处方号查询体检表
     * @param prescription_num
     * @return
     */
    MedicalExamination selectByprescription_num(String prescription_num);
    /**
     *根据就诊id 查询体检表
     * @param registerId
     * @return
     */
    MedicalExamination selectByRegisterId(Integer registerId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MedicalExamination queryById(Integer id);

    /**
     * 分页查询
     *
     * @param medicalExamination 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    Page<MedicalExamination> queryByPage(MedicalExamination medicalExamination, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param medicalExamination 实例对象
     * @return 实例对象
     */
    MedicalExamination insert(MedicalExamination medicalExamination);

    /**
     * 修改数据
     *
     * @param medicalExamination 实例对象
     * @return 实例对象
     */
    MedicalExamination update(MedicalExamination medicalExamination);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
