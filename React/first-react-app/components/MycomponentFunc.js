import React from 'react';

const MycomponentFunc = ({name,age}) => {
//function MycomponentFunc(props) {
    return (
        <div>
            부모로부터 받는 상태변수 {name},{age}
        </div>
    );
}

export default MycomponentFunc;