// Alert.js
import React from 'react';
import './component-css/Message.css';

const Message = ({ type, message }) => {
  const alertClassName = `alert ${type || 'default'}`;

  return (
    <div className={alertClassName}>
      {message}
    </div>
  );
};

export default Message;
