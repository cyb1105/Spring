import React, { Component } from 'react';
import TodoItem from './TodoItem';

class TodoItemList extends Component {
    //life-cycle메서드 overriding : render() 메서드의 호출을 줄일 수 있다.
    shouldComponentUpdate(nextProps,nextState){
        return this.props.todos !== nextProps.todos;

    }
    render() {
        const {todos, myToggle, myRemove} = this.props;
        const todoList = todos.map(({id, checked,text})=>(
            <TodoItem id={id} checked={checked} todoText={text} myToggle={myToggle} myRemove={myRemove} key={id}/>
        ));
        return (
            <div>
                {todoList}
            </div>
        );
    }
}

export default TodoItemList;