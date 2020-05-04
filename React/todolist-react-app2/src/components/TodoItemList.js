import React, { Component } from 'react';
import TodoItem from './TodoItem';
import { connect } from 'react-redux'
import { fetchAllTodos } from '../actions'

class TodoItemList extends Component {
    //life-cycle메서드 
    componentDidMount() {
        this.props.fetchAllTodos();
    }

    //life-cycle메서드 overriding : render() 메서드의 호출을 줄일 수 있다.
    shouldComponentUpdate(nextProps, nextState) {
        return this.props.todos !== nextProps.todos;

    }
    render() {
        const { todos } = this.props;
        const todoList = todos.map(({ id, checked, text }) => (
            <TodoItem id={id} checked={checked} todoText={text}  key={id} />
        ));
        return (
            <div>
                {todoList}
            </div>
        );
    }
}

const mapStateToProps = state => {
    return {
        todos: state.todos
    }
}

export default connect(mapStateToProps, { fetchAllTodos })(TodoItemList);
