Ans 1:
SELECT SUM(claimed_charge)
FROM document
WHERE status = 'EXPORTED';

Ans 2:
SELECT d.insured_name, d.insured_address, d.claimed_charge
FROM document d JOIN batch b
ON b.id = d.batch_id
WHERE d.status = 'TO_REPRICE' and b.customer_id IN (1,2);
