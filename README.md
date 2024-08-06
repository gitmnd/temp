[
  {
    $match:
     {
        _id: "A",
      },
  },
  {
    $unwind: {
      path: "$Records",
    },
  },
  { $match: { "Records.service_type": "W" } },
  {
    $group: {
      _id: "$_id",
      service_type: { $first: "$service_type" },
      Records: { $push: "$Records" }
    }
  }
]
