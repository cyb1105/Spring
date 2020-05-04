import axios from 'axios';
//Action type 정의
export const FETCH_TODOS = "FETCH_TODOS";
export const ADD_TODO = "ADD_TODO";
export const REMOVE_TODO = "REMOVE_TODO";
export const TOGGLE_TODO = "TOGGLE_TODO";


//server url
const apiUrl = process.env.REACT_APP_APIURL;
//'http://localhost:8083/todos';

//Action 생성함수 선언

//4.토글링
export const toggleTodo = todo => {
    return (dispatch) => {
        axios.put(`${apiUrl}/${todo.id}`, todo)
            .then(res => {
                dispatch({
                    type: TOGGLE_TODO,
                    payload: res.data
                })
            })
            .catch(error => {
                console.log(error);
                throw (error);
            })
    }
}

//3.todo삭제
export const removeTodo = id => {
    return (dispatch) => {
        axios.delete(`${apiUrl}/${id}`)
            .then(res => {
                dispatch({
                    type: REMOVE_TODO,
                    payload: res.data
                })
            })
            .catch(error => {
                console.log(error);
                throw (error);
            })
    }
}


//2.todo등록
export const addTodo = (todo) => {
    return (dispatch) => {
        axios.post(apiUrl, todo)
            .then(res => {
                dispatch({
                    type: ADD_TODO,
                    payload: res.data
                })
            })
            .catch(error => {
                console.log(error);
                throw (error);
            })
    }
}


//1.todo 목록
export const fetchAllTodos = () => {
    return (dispatch) => {
        axios.get(apiUrl)
            .then(res => {
                dispatch({
                    // 요청이 성공하면, 서버 응답내용을 payload로 설정하여
                    // FETCH_TODOS 액션을 디스패치 합니다.
                    type: FETCH_TODOS,
                    payload: res.data
                })
            })
            .catch(error => {
                console.log(error);
                throw (error);
            })
    }
}