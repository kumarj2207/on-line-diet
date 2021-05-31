package com.assignment.diet.response.vo;

import java.util.ArrayList;
import java.util.List;

import com.assignment.diet.entity.Batch;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BatchResponseVO {

	private Long batchId;
	private String name;
	private Long parentBatchId;
	private String parentBatchName;
	@JsonIgnore
	private List<Batch> batches;
	
	private List<LocalBatch> localBatches = new ArrayList<>();

	public BatchResponseVO(Long batchId, String name, Long parentBatchId, String parentBatchName) {
		this.batchId = batchId;
		this.name = name;
		this.parentBatchId = parentBatchId;
		this.parentBatchName = parentBatchName;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentBatchName() {
		return parentBatchName;
	}

	public void setParentBatchName(String parentBatchName) {
		this.parentBatchName = parentBatchName;
	}

	public Long getParentBatchId() {
		return parentBatchId;
	}

	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		for (Batch batch : batches) {
			LocalBatch localBatch = new LocalBatch();
			localBatch.setBatchId(batch.getBatchId());
			localBatch.setName(batch.getName());
			localBatch.setParent(
					getParentBatchId() == batch.getBatchId() && getParentBatchName().equals(batch.getName()));
			this.localBatches.add(localBatch);
		}
	}

	private class LocalBatch {
		private Long batchId;
		private String name;
		private boolean isParent;

		public Long getBatchId() {
			return batchId;
		}

		public void setBatchId(Long batchId) {
			this.batchId = batchId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isParent() {
			return isParent;
		}

		public void setParent(boolean isParent) {
			this.isParent = isParent;
		}
	}

	public List<LocalBatch> getLocalBatches() {
		return localBatches;
	}

	public void setLocalBatches(List<LocalBatch> localBatches) {
		this.localBatches = localBatches;
	}

	public void setParentBatchId(Long parentBatchId) {
		this.parentBatchId = parentBatchId;
	}

}
