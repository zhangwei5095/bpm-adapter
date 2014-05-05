package org.bpm;

import org.bpm.vo.BpmProcess;

import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by rocky on 14-5-4.
 */
public interface BpmDefinition {

    /**
     * 部署流程
     * @param category 分类，可为空
     * @param zipInputStream zip中包含.bpmn和.png图片
     * @param tenantId 多租户ID，可为空
     */
    public void deploy(String category, ZipInputStream zipInputStream , String tenantId);

    /**
     * 流程部署数据
     * @param tenantId 多租户ID 可为空，为空则查询所有
     * @return
     */
    public List<BpmProcess> allBpmProcesses(String tenantId);
}
