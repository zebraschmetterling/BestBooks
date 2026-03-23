<script lang="ts">
    let { children } = $props();
</script>

<style>
    :global(.loading) {
        text-align: center;
    }
    :global(.error) {
        text-align: center;
        color: #b5373f;
    }
</style>


{@render children()}