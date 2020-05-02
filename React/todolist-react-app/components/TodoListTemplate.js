import React from 'react';
import './TodoListTemplate.css';

//function TodoListTemplate(props) {
const TodoListTemplate = ({form,children}) => {
    return (
        <div className="todo-list-template">
            <div className="title">
                LIST
            </div>
            <div className="form-wrapper">
                {form}
            </div>
            <div className="todos-wrapper">
                {children}
            </div>
        </div>
    );
}

export default TodoListTemplate;