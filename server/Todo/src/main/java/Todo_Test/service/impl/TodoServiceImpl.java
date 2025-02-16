package Todo_Test.service.impl;

import Todo_Test.mapper.TodoMapper;
import Todo_Test.pojo.Todo;
import Todo_Test.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoMapper todomapper;

    public List showAllTodos() {
        return todomapper.selectAllTodos();
    }

    public int addTodo(Todo todo) {
        return todomapper.insertTodo(todo);
    }
    public List searchTodos(String keyword) {
        return todomapper.selectTodosByKey(keyword);
    }
    public int deleteTodo(int id) {
        return todomapper.deleteTodoById(id);
    }
    public int updateTodo(Todo todo) {
        return todomapper.changeTodo(todo);
    }
    public Todo showOneTodo(int id) {
        return todomapper.selectTodoById(id);
    }
}
