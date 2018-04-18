	public Page<Payment> findExportPaymentList(Pageable pageable) {
		if (pageable == null) {
			return new Page<Payment>();
		}
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		setExportParameterMapFromPageable(pageable, parameterMap);//��ҳ��ѯ������������Ҫ��ҳ

		List<Payment> paymentList = paymentDAO.findPaymentList(parameterMap);
		if (paymentList == null || paymentList.size() == 0) {
			return new Page<Payment>();
		}
		
		doPaymentList(paymentList, false);

		Page<Payment> page = new Page<Payment>(paymentList, paymentList.get(0)
				.getTotalCount(), pageable);
		return page;
	}