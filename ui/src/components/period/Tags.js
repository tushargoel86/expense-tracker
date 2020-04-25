import React from "react";
import Tag from "./Tag";

const tags = (props) => {
  var items = props.tags.map((tg, index) => {
    return (
      <Tag
        key={tg.tagId.id + index}
        tag={tg}
        periodId={props.periodId}
        userId={props.userid}
        fetchDetails={props.fetchDetails}
      />
    );
  });

  return items;
};

export default tags;
