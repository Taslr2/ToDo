package Todo_Test.service;

import Todo_Test.pojo.Todo;

import java.util.List;

public interface TodoService {
    List showAllTodos();
    Todo showOneTodo(int id);
    int addTodo(Todo todo);
    List searchTodos(String keyword);
    int deleteTodo(int id);
    int updateTodo(Todo todo);
  //  int markTodo_as_deleted(int id);
}
