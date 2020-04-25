import React from "react";
import { Container, Row } from "react-bootstrap";

import CardLayout from "./CardLayout";

const envelope = (props) => {
  return (
    <Container fluid className="mt-2">
      <Row className="justify-content-center">
         <CardLayout {...props} styleName={{ width: "80%", background: 'black' }} />
      </Row>
    </Container>
  );
};

export default envelope;
