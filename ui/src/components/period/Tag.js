import React from "react";
import RemoveTransaction from "./RemoveTransaction";

export const Transaction = (props) => {
  const { requestData } = props;
  return (
    <tr>
      <td>{requestData.transactionDate.transactionDate} </td>
      <td>{requestData.money}</td>
      <td>{requestData.description}</td>
      <td>{requestData.tagName}</td>
      <td>
        <RemoveTransaction
          requestData={requestData}
          fetchDetails={props.fetchDetails}
        />
      </td>
    </tr>
  );
};

const tag = (props) => {
  const tagValue = props.tag;

  return tagValue.transactions.map((trx, index) => {
    const { tagName, tagId } = tagValue;
    const { userId, periodId } = props;
    const request = {
      tagName,
      tagId,
      ...trx,
      userId,
      periodId,
    };

    return (
      <Transaction
        key={trx.transactionId.id + index}
        requestData={request}
        fetchDetails={props.fetchDetails}
      />
    );
  });
};

export default tag;
