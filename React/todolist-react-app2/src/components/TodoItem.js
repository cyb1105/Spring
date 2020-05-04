import React, { Component } from 'react';
import { connect } from 'react-redux';
import { removeTodo, toggleTodo } from '../actions';

import './TodoItem.css'

class TodoItem extends Component {
    shouldComponentUpdate(nextProps, nextState) {
        return this.props.checked !== nextProps.checked;
    }
    handleRemove = (id) => {
        this.props.removeTodo(id);
    }
    handleToggle = (todo) => {
        this.props.toggleTodo(todo)
    }

    render() {
        const { todoText, checked, id } = this.props
        return (
            <div className="todo-item" onClick={() => {
                const todo = { text:todoText, checked, id };
                todo.checked = !todo.checked;
                this.handleToggle(todo)
            }}>
                <div className="remove" onClick={(e) => { e.stopPropagation(); this.handleRemove(id) }}>
                    &times;
                </div>
                <div className={`todo-text ${checked && 'checked'}`}>
                    <div>{todoText}</div>
                </div>
                {
                    checked && (<div className="check-mark">✓</div>)
                }
            </div>
        );
    }
}

export default connect(null, { removeTodo, toggleTodo })(TodoItem);