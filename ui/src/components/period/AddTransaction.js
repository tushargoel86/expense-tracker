import React from "react";
import TransactionModal from "../ui/transactionform/TransactionModal";

const addTransaction = React.memo((props) => {
  return (
    <TransactionModal
      startDate={props.startDate}
      endDate={props.endDate}
      userId={props.userId}
      fetchDetails={props.fetchDetails}
      loadSpinner={props.loadSpinner}
    />
  );
});

export default addTransaction;
