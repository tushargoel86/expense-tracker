import React from "react";
import { Form, Col, Row } from "react-bootstrap";

import TransactionDate from "./TransactionDate";

const Inp = (props) => {
  var label = props.label;
  var component = "";

  const handleChange = (e) => {
    props.handleChange(e.target.value);
  };

  switch (props.type) {
    case "input":
      component = (
        <Form.Control
          required
          type="text"
          name={props.name}
          onChange={handleChange}
        />
      );
      break;
    case "input-number":
      component = (
        <Form.Control
          required
          type="number"
          name={props.name}
          onChange={handleChange}
        />
      );
      break;
    case "date":
      component = (
        <TransactionDate name={props.name} onChange={props.handleChange} />
      );
      break;
    case "text-area":
      component = (
        <Form.Control as="textarea" name={props.name} onChange={handleChange} />
      );
      break;
    case "control":
      component = (
        <Form.Control as="select" name={props.name} onChange={handleChange}>
          <option value="EXPENSE">EXPENSE</option>
          <option value="RECEIVED">RECEIVED</option>
        </Form.Control>
      );
      break;
    default: break;
  }

  return (
    <Form.Group as={Row}>
      <Col md="4">
        <Form.Label>{label} </Form.Label>
      </Col>
      <Col md="4">{component}</Col>
    </Form.Group>
  );
};

export default Inp;
