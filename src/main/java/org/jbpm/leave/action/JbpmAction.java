package org.jbpm.leave.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zwl.vo.ProcessVo;

public class JbpmAction extends ActionSupport
{
    
    private ProcessEngine processEngine;
    private RepositoryService repositoryService;
    private ExecutionService executionService;
    private TaskService taskService ;
    private String id;
    private List<ProcessDefinition> processDefinitionList;
    private List<ProcessInstance> processInstanceList;
    private List<Task> taskList;
    private ActivityCoordinates ac; 
    private ProcessVo processVo;
    private Map<String, Object> map;
    private String result;
    
    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

  

    public ActivityCoordinates getAc()
    {
        return ac;
    }

    public List<ProcessInstance> getProcessInstanceList()
    {
        return processInstanceList;
    }

    public List<Task> getTaskList()
    {
        return taskList;
    }

    private void init()
    {
        repositoryService=processEngine.getRepositoryService();
        executionService=processEngine.getExecutionService();
        taskService=processEngine.getTaskService();
    }
    
    public List<ProcessDefinition> getProcessDefinitionList()
    {
        return processDefinitionList;
    }

    private String userName;
    
    public String getUserName()
    {
        return userName;
    }


    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    @SuppressWarnings("unchecked")
    public String login()
    {
        Map map=ActionContext.getContext().getSession();
        map.put("user", userName);
        return SUCCESS;
    }
    
    /**
     * ������ҳ
     * @return
     */
    public String loadIndexPage()
    {
        init();
        processDefinitionList=repositoryService.createProcessDefinitionQuery().list();
        processInstanceList=executionService.createProcessInstanceQuery().list();
        
        
        //����ģ�ⲻͬ�Ľ�ɫ��½ϵͳ����ӵ���ͬ�Ĵ������
        String manager="zwllxs1,zwllxs2,zwllxs3,zwllxs4";
        String boss="zhangweilin,zhangweilin2,zhangweilin3,zhangweilin4";
        String roleName="";//Ҫ������ȥ���������ڵĽڵ�
        
        String name=getLoginedUserName();
        
        //�����½
        if(manager.contains(name))
        {
            roleName="manager";
        }
        //�ϰ��½
        else if(boss.contains(name))
        {
            roleName="boss";
        }
        //��ͨ�û�
        else
        {
            roleName=name;
        }
        
        System.out.println("roleName: "+roleName);
        taskList=taskService.findPersonalTasks(roleName);
        System.out.println("taskList: "+taskList);
        return SUCCESS;
    }
    
    /**
     * ����
     * @return
     */
    public String deploy()
    {
        System.out.println("����");
        init();
//        String did=repositoryService.createDeployment().addResourceFromClasspath("leave2.jpdl.xml").deploy();
        ZipInputStream zis = new ZipInputStream(this.getClass().getResourceAsStream("/leave2.zip"));
        //�������̣���������Ԥ�������񣬼���ϵͳ�д���һ�����̣�����ȫ�ֵģ������ĵ�½ �û��޹ء�Ȼ������������ʱ�������½�û���������
        String did=repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
        
        //ʹ��zip��ʽ
//        ZipInputStream zis = new ZipInputStream(this.getClass().getResourceAsStream("/leave2.zip"));
//        //�������̣���������Ԥ�������񣬼���ϵͳ�д���һ�����̣�����ȫ�ֵģ������ĵ�½ �û��޹ء�Ȼ������������ʱ�������½�û���������
//        String did=repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
    
        System.out.println("did: "+did);
        return SUCCESS;
    }
    
    /**
     * ����
     * @return
     */
    @SuppressWarnings("unchecked")
    public String start()
    {
        init();
        Map map = new HashMap();
        //����������ʱ�����ھ���һ���û�Ҫ����ˣ���ô����ʱ��Ҫ��������Ϣ���������û��ϣ��ڿ�ʼ����һ���ڵ㣨Ҳ���ǵ�һ������ڵ㣩����ָ�ɸ������û���Ҫ�����Ӧ�ı�����������
        map.put("owner", getLoginedUserName());
        System.out.println("id_: "+id);
        ProcessInstance processInstance=executionService.startProcessInstanceById(id, map);
        System.out.println("����ʱprocessInstance: "+processInstance.getId());
        return SUCCESS;  
    }
    
    /**
     * 
     * @return
     */
    public String manager()
    {
        init();
        Task task = taskService.getTask(id);
        String taskId=task.getId();
        Set<String> strSet=new HashSet<String>();
        strSet.add("owner");
        strSet.add("day");
        strSet.add("reason");
        strSet.add("name");
        strSet.add("sex");
        strSet.add("age");
        map=taskService.getVariables(taskId, strSet);
        System.out.println("map: "+map);
        return SUCCESS;
    }
    
    /**
     * �ϰ�����
     * @return
     */
    public String boss()
    {
        init();
        Task task = taskService.getTask(id);
        String taskId=task.getId();
        Set<String> strSet=new HashSet<String>();
        strSet.add("owner");
        strSet.add("day");
        strSet.add("reason");
        strSet.add("name");
        strSet.add("sex");
        strSet.add("age");
//        taskService.getVariable(taskId, "owner");
        map=taskService.getVariables(taskId, strSet);
        return SUCCESS;
    }
    
    /**
     * �ϰ崦��
     * @return
     */
    public String submitBoss()
    {
        init();
        taskService.completeTask(id);
        return SUCCESS;
    }
    
    /**
     * �����ύ 
     * @return
     */
    public String submitManager()
    {
        init();
        taskService.completeTask(id, result);
        return SUCCESS;
    }
    
    /**
     * �Ƴ�
     * @return
     */
    public String remove()
    {
        init();
        repositoryService.deleteDeploymentCascade(id);
        return SUCCESS;
    }
    
    /**
     * ��ʾ
     * @return
     */
    public String view()
    {
        init();
      //ͨ��id�鵽����ʵ��
        ProcessInstance processInstance = executionService.findProcessInstanceById(id);
        Set<String> activityNames = processInstance.findActiveActivityNames();
        
        //CoordinatesΪ��������
        ac = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityNames.iterator().next());
        return SUCCESS;
    }
    
    /**
     * ��ʾͼƬ
     * @throws IOException
     */
    public void pic() throws IOException
    {
        init();
        ProcessInstance processInstance = executionService.findProcessInstanceById(id);
        String processDefinitionId = processInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
        String filePath="leave2.png";
        //�˴���֪��Ϊʲô��Ҫ�ر��ͨ��repositoryService��processDefinition.getDeploymentId()����ʾ��ͼƬ
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),filePath);
        byte[] b = new byte[1024];
        int len = -1; 
        ServletOutputStream sos=ServletActionContext.getResponse().getOutputStream();
        while ((len = inputStream.read(b, 0, 1024)) != -1)
        {
            sos.write(b, 0, len);
        }
        sos.close();
    }
    
    
    /**
     * �ύ
     * @return
     */
    @SuppressWarnings("unchecked")
    public String submit()
    {
        init();
        Map map = new HashMap();
        //��day, ��xml�ļ��У���һ��decision��ǩ��������һ�����ʽ:expr="#{day > 3 ? '�ϰ�����' : '����'}" 
        //��������ʽ�е�day����
        map.put("day", processVo.getDay());
        map.put("reason", processVo.getReason());
        map.put("name", processVo.getOwner());
        map.put("age", processVo.getAge());
        map.put("sex", processVo.getSex());
        //���ڶ�������Ϊ�ַ�����ָ��Ҫ���ĸ�������ɣ�����ָ��Ҫ��testhaha���,�������������ָ�������֣�
        //��ֻ�ܰ�������ȥָ������,���û�����֣�������map,
        //Ҳ����˵�����ݵĲ���Ϊmap,�����̻�ȥѰ��û������Ƶ������ߣ����û�ҵ������׳�No unnamed transitions were found for the task �쳣
        //taskService.completeTask(taskId, "testhaha");
        //���ڶ�������Ϊmap,���ʾֻ�Ǵ��ݲ������
        System.out.println("�ύ���");
        taskService.completeTask(processVo.getTaskId(), map);
        return SUCCESS;
    }
    
    public String getLoginedUserName()
    {
        return (String) ActionContext.getContext().getSession().get("user");
    }
    
    public void setProcessEngine(ProcessEngine processEngine)
    {
        this.processEngine = processEngine;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 7726442371304781094L;

    public ProcessVo getProcessVo()
    {
        return processVo;
    }

    public void setProcessVo(ProcessVo processVo)
    {
        this.processVo = processVo;
    }

    public Map<String, Object> getMap()
    {
        return map;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
    
    
    

}
