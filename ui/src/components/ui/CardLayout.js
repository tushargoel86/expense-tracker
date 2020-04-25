import React from "react";
import { Card } from "react-bootstrap";

const cardlayout = (props) => {
  return (
    <Card border="primary" style={props.styleName} bg={props.bg}>
      <Card.Body className="text-center">
        <Card.Title className="text-white">{props.title}</Card.Title>
        {props.children}
      </Card.Body>
    </Card>
  );
};

export default cardlayout;
