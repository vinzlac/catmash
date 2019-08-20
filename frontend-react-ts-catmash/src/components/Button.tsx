import React from 'react';
import './Button.scss';

interface ButtonProps {
  type?: 'secondary' | 'big' | 'back' | 'header' | 'header-secondary';
  text: string;
}

const Button = ({
  type,
  text,
}: ButtonProps) => {
  const buttonClass = type ? `button-${type}` : 'button';

  return (
    <button className={`${buttonClass}`}>{text}
    </button>
  );
};

export default Button;
