import React from "react";
import { Button } from "react-bootstrap";
import { deleteTransaction } from "../../actions/Action";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'
import { library } from "@fortawesome/fontawesome-svg-core";

library.add(faTrash);

const removeTransaction = (props) => {
  const { periodId, userId, tagId, transactionId } = props.requestData;
  const handleRemoveTransaction = () => {
    deleteTransaction(periodId.id, userId, tagId.id, transactionId.id)
      .then((res) => res.json())
      .then((res) => {
        props.fetchDetails();
      })
      .catch((err) => console.log(err));
  };
  return (
    <Button icon="magic" onClick={handleRemoveTransaction} size="sm">
      <FontAwesomeIcon icon="trash"/>
    </Button>
  );
};

export default removeTransaction;
