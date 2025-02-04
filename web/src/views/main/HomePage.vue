<script setup>
import HeadNavigation from '@/components/HeadNavigation.vue';
import SideNavigation from '@/components/sidebar/SideNavigation.vue';
import { ref,provide } from 'vue';

const isSidebarVisible = ref(true);

const handleUpdateVisibility = (visibility) => {
  isSidebarVisible.value = visibility;
};

const tasks = ref([
    {
        "title": "完成项目报告",
        "details": "撰写并提交项目的最终报告。",
        "isCompleted": false,
        "completionDate": null,
        "category": "工作",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-10T14:00:00Z"
    },
    {
        "title": "回复客户邮件",
        "details": "回复客户关于项目进度的询问邮件。",
        "isCompleted": true,
        "completionDate": "2023-10-05T14:30:00Z",
        "category": "工作",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-05T14:30:00Z"
    },
    {
        "title": "准备会议材料",
        "details": "为下周的项目会议准备所需材料。",
        "isCompleted": false,
        "completionDate": null,
        "category": "工作",
        "isDeleted": true,
        "expectedCompletionDate": "2023-10-08T10:00:00Z"
    },
    {
        "title": "更新软件版本",
        "details": "将应用软件更新到最新版本。",
        "isCompleted": false,
        "completionDate": null,
        "category": "生活",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-12T09:00:00Z"
    },
    {
        "title": "健身训练",
        "details": "进行一小时的健身锻炼。",
        "isCompleted": true,
        "completionDate": "2023-10-05T17:00:00Z",
        "category": "生活",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-05T17:00:00Z"
    },
    {
        "title": "阅读新书",
        "details": "阅读《深入浅出Vue.js》一书。",
        "isCompleted": false,
        "completionDate": null,
        "category": "学习",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-15T20:00:00Z"
    },
    {
        "title": "整理办公桌",
        "details": "整理和清理办公桌，保持整洁。",
        "isCompleted": true,
        "completionDate": "2023-10-05T12:00:00Z",
        "category": "生活",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-05T12:00:00Z"
    },
    {
        "title": "参加在线课程",
        "details": "报名并参加有关前端开发的在线课程。",
        "isCompleted": false,
        "completionDate": null,
        "category": "学习",
        "isDeleted": true,
        "expectedCompletionDate": "2023-10-10T08:00:00Z"
    },
    {
        "title": "计划周末出行",
        "details": "为周末的出行制定具体计划。",
        "isCompleted": false,
        "completionDate": null,
        "category": "生活",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-06T18:00:00Z"
    },
    {
        "title": "提交预算申请",
        "details": "准备并提交下一季度的预算申请。",
        "isCompleted": false,
        "completionDate": null,
        "category": "工作",
        "isDeleted": false,
        "expectedCompletionDate": "2023-10-13T10:00:00Z"
    }
]);

provide('tasks', tasks);

</script>

<template>
  <HeadNavigation />
  <div class="main-container">
    <div :class="['sidebar', { 'hidden-sidebar': !isSidebarVisible }]">
      <SideNavigation @updateVisibility="handleUpdateVisibility" />
    </div>
    <div class="content">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
body,
html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: white;
}

.main-container {
  display: flex;
  height: calc(100vh - 48px);
}

.sidebar {
  width: 290px;
  background-color: #fff;
  transition: width 0.3s;
}

.hidden-sidebar {
  width: 0;
  overflow: hidden;
}

.content {
  width: 100%;
}
</style>
