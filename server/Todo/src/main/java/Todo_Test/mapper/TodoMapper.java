package Todo_Test.mapper;

import Todo_Test.pojo.Todo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface TodoMapper /*extends BaseMapper<Todo>*/{
    List selectAllTodos();
    int insertTodo(Todo todo);
    Todo selectTodoById(int id);
    List selectTodosByKey(String keyword);
    int deleteTodoById(int id);
    int changeTodo(Todo todo);
 //   int markTodoAsDeleted(int id);
}
