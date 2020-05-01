import React, { Component } from 'react';
import PropTypes from 'prop-types'
import '../index.css';
class MyComponent extends Component {
    static defaultProps ={
        name:'리액트defult'
    }
    static propTypes ={
        name:PropTypes.string,
        age:PropTypes.number.isRequired
    }
    //state variable
    state={
        number:0,
        message:'',
        messages:['Angular','React','Vue','Ember']
    }
    handleDecrease=(event) => {
        // const {number} = this.state
        // this.setState({number:number-1});
        console.log(event.target.value)
        this.setState({number:this.state.number-1});
    }
    handleChange=(event)=>{
        this.setState({
            message: event.target.value
        })
    }
    handleInsert=()=>{
        const {message,messages} = this.state;
        this.setState({
            messages:messages.concat(message),
            message:''
        });
    }
    handelEnter=(e)=>{
        // if(e.key === 'Enter'){
        //     this.handleInsert();
        // }
        if(e.keyCode ===13){
            this.handleInsert();
        }
    }
    handleRemove = (index)=>{
        this.setState({
            messages: this.state.messages.filter((item,idx)=>(idx !== index))
        });
    }
    render() {
        const {name,age} = this.props
        const {number, message, messages} = this.state;
        const {handleDecrease,handleChange,handleInsert,handelEnter,handleRemove} = this
        const msgList = messages.map((msg,idx)=>(
            <li key={idx} onDoubleClick={()=>(handleRemove(idx))}>{msg}</li>
        ))

        return (
            <div>
               Hello {name} {age} 
               <p>Number 값은: {number}</p>
               <button onClick={()=>(this.setState({number:number+1}))}>증가</button>
               <button onClick={handleDecrease} value="decrease">감소</button><hr/>

               <button onClick={()=>(this.mymessage.focus())}>텍스트 창 포커스 주기</button><br/>
               <input type="text" value={message} onChange={handleChange} ref={
                   (ref)=>this.mymessage=ref} 
                //    onKeyPress={handelEnter}
                onKeyDown={handelEnter}/>
               <button onClick={handleInsert}>추가</button>
               <button onClick={()=>(this.setState({message:''}))}>초기화</button>
               <ul>
                   {msgList}
               </ul>
            </div>
        );
    }
}
// MyComponent.defaultProps = {
//     name:'리액트'
// };
// MyComponent.propTypes = {
//     name:PropTypes.string
// };

export default MyComponent;