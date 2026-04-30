package Objects.RecordTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditedRecordDecorator extends RecordDecorator {
    private static final DateTimeFormatter AUDIT_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private String reviewedBy;
    private String auditNote;
    private LocalDateTime auditTime;

    public AuditedRecordDecorator(Record record, String reviewedBy, String auditNote) {
        super(record);
        this.reviewedBy = reviewedBy;
        this.auditNote = auditNote;
        this.auditTime = LocalDateTime.now().withNano(0);
    }

    @Override
    public String toString() {
        return wrappedRecord.toString() +
                "\n[Audit Info] Reviewed by: " + reviewedBy +
                " | Note: " + auditNote +
                " | Time: " + auditTime.format(AUDIT_TIME_FORMAT);
    }
}
