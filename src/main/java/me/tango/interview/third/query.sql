-- Given:
-- There is the table schema below that stores "view" events from users from different regions.
-- Need to write query to show 5 regions with biggest number of unique "views" for the last month
-- sorted by these numbers descending. It is possible to update schema to speed up query.

-- query:
SELECT r.name, COUNT(vs.id)
FROM viewer_stats vs
         INNER JOIN regions r ON r.id = vs.region_id
WHERE vs.created BETWEEN (CURRENT_TIMESTAMP - INTERVAL '1 month') and CURRENT_TIMESTAMP
GROUP BY r.id
ORDER BY SUM(vs.id) DESC;