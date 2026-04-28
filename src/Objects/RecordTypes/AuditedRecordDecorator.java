package Objects.RecordTypes;

public class AuditedRecordDecorator extends RecordDecorator {

    private String reviewedBy;
    private String auditNote;

    public AuditedRecordDecorator(Record record, String reviewedBy, String auditNote) {
        super(record);
        this.reviewedBy = reviewedBy;
        this.auditNote = auditNote;
    }

    @Override
    public String toString() {
        return wrappedRecord.toString() +
                "\n[Audit Info] Reviewed by: " + reviewedBy +
                " | Note: " + auditNote;
    }
}