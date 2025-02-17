# ToDo
Owner-pro project

# Todo API

## 后端服务器端口号

- **URL:** `http://localhost:8080`

## 获取所有待办事项

- **URL:** `/showTodos`
- **方法:** `GET`
- **响应:** `List<Todo>`

## 通过ID获取待办事项

- **URL:** `/show`
- **方法:** `GET`
- **参数:**
  - `id` (int): 待办事项的ID。
- **响应:** `Todo`

## 保存新的待办事项

- **URL:** `/save`
- **方法:** `POST`
- **参数:**
  - `todo` (Todo): 待保存的待办事项对象。
- **响应:** `String` ("success" 或 "fail")

## 搜索待办事项

- **URL:** `/search`
- **方法:** `GET`
- **参数:**
  - `keyword` (String): 搜索关键字。
- **响应:** `List<Todo>`

## 删除待办事项

- **URL:** `/delete`
- **方法:** `DELETE`
- **参数:**
  - `id` (int): 要删除的待办事项ID。
- **响应:** `String` ("success" 或 "fail")

## 更新待办事项

- **URL:** `/update`
- **方法:** `PUT`
- **参数:**
  - `todo` (Todo): 待更新的待办事项对象。
- **响应:** `String` ("success" 或 "fail")
