<link rel="stylesheet" type="text/css" href="/assets/css/jquery.dataTables.css">
<script src="/assets/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf8" src="/assets/js/jquery.dataTables.js"></script>

<script>
        $(document).ready(function() {
            $('#table-id').DataTable({
                // Activer la recherche multi-mots
                searching: true,
                // Activer le triage sur toutes les colonnes
                ordering: true
            });
        });
</script>