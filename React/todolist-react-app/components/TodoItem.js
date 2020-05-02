import React, { Component } from 'react';
import './TodoItem.css'

class TodoItem extends Component {
    shouldComponentUpdate(nextProps, nextState) {
        return this.props.checked !== nextProps.checked;
    }
    
    render() {
        const { todoText, checked, id, myToggle, myRemove } = this.props
        return (
            <div className="todo-item" onClick={() => myToggle(id)}>
                <div className="remove" onClick={(e) => { e.stopPropagation(); myRemove(id); }}>
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

export default TodoItem;