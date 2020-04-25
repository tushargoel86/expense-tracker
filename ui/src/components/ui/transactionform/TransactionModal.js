import React, { useState } from "react";
import { Modal, Button, Form, Row, Col } from "react-bootstrap";
import Transaction from "../../period/Transaction";

const TransactionModal = (props) => {
  const [show, setShow] = useState(false);
  const handleClose = () => {
    props.loadSpinner(false);
    setShow(false);
  };
  const handleShow = () => setShow(true);

  return (
    <>
      <Form.Group>
        <Row>
          <Col md="8"></Col>
          <Col className="justify-content-end">
            <Button variant="primary" onClick={handleShow}>
              Add Transaction
            </Button>
          </Col>
          <Col></Col>
        </Row>

        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Add Transaction</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Transaction
              startDate={props.startDate}
              endDate={props.endDate}
              userId={props.userId}
              close={handleClose}
              fetchDetails={props.fetchDetails}
              loadSpinner={props.loadSpinner}
            />
          </Modal.Body>
        </Modal>
      </Form.Group>
    </>
  );
};

export default TransactionModal;
