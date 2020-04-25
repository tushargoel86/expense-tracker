import React, { useState, useCallback } from "react";
import Inp from "../ui/transactionform/Inp";
import { Form, Button, Alert } from "react-bootstrap";
import { convertDateFormat } from "../../constants/utility/Utility";
import { addTransaction } from "../../actions/Action";

const Transaction = React.memo((props) => {
  const [trxDate, setTrxDate] = useState(new Date());
  const [amount, setAmount] = useState();
  const [transactionType, setTransactionType] = useState("EXPENSE");
  const [description, setDescription] = useState();
  const [tag, setTag] = useState();
  const { startDate, endDate, userId } = props;
  const [errorMessage, setErrorMessage] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit();
  };

  const onSubmit = useCallback(() => {
    props.loadSpinner(true);
    const transactionDate = convertDateFormat(trxDate);
    const userData = {
      startDate,
      endDate,
      userId,
      transactionDate,
      amount,
      transactionType,
      description,
      tag,
    };

    addTransaction(userData)
      .then((res) => res.json())
      .then((res) => {
        console.log(errorMessage);
        if (res.message.indexOf("successful") !== -1) {
          props.fetchDetails();
          props.close();
        } else {
          setErrorMessage(res.message);
        }
      })
      .catch((msg) => setErrorMessage(msg));
  });

  const handleCancel = () => {
    setErrorMessage();
    props.close();
  };

  return (
    <Form className="justify-as-center" onSubmit={handleSubmit}>
      {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
      <Inp
        label="Transaction Date:"
        type="date"
        name="transactionDate"
        handleChange={setTrxDate}
      />
      <Inp
        label="Amount:"
        type="input-number"
        name="amount"
        handleChange={setAmount}
      />
      <Inp
        label="Transaction Type:"
        type="control"
        name="transactionType"
        handleChange={setTransactionType}
      />
      <Inp
        label="Description:"
        type="text-area"
        name="description"
        handleChange={setDescription}
      />

      <Inp label="Tag:" type="input" name="tag" handleChange={setTag} />
      <Form.Group>
        <Button variant="secondary" onClick={handleCancel}>
          Cancel
        </Button>{" "}
        <Button type="submit" variant="primary">
          Add Transaction
        </Button>
      </Form.Group>
    </Form>
  );
});

export default Transaction;
